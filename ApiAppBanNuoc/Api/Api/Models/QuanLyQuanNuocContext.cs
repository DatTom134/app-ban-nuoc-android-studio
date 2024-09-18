using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Runtime.CompilerServices;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace Api.Models;

public partial class QuanLyQuanNuocContext : DbContext
{
    public string ConnString;
    public QuanLyQuanNuocContext()
    {
        var configuration = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                    .AddJsonFile("appsettings.json", false)
                        .Build();

        ConnString = configuration.GetConnectionString("ChuoiKetNoi");
    }

    public QuanLyQuanNuocContext(DbContextOptions<QuanLyQuanNuocContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Chitiethoadon> Chitiethoadons { get; set; }

    public virtual DbSet<Danhmuc> Danhmucs { get; set; }

    public virtual DbSet<Hoadon> Hoadons { get; set; }

    public virtual DbSet<Quanli> Quanlis { get; set; }

    public virtual DbSet<Sanpham> Sanphams { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseSqlServer("Data Source=DTOM;Initial Catalog=QuanLyQuanNuoc;Integrated Security=True;Trust Server Certificate=True");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Chitiethoadon>(entity =>
        {
            entity.HasKey(e => e.CthdId);

            entity.ToTable("CHITIETHOADON");

            entity.Property(e => e.CthdId).HasColumnName("CTHD_ID");
            entity.Property(e => e.HdId).HasColumnName("HD_ID");
            entity.Property(e => e.Soluong).HasColumnName("SOLUONG");
            entity.Property(e => e.SpIp).HasColumnName("SP_IP");
            entity.Property(e => e.Tongtien).HasColumnName("TONGTIEN");

            entity.HasOne(d => d.Hd).WithMany(p => p.Chitiethoadons)
                .HasForeignKey(d => d.HdId)
                .HasConstraintName("FK_CHITIETHOADON_HOADON1");

            entity.HasOne(d => d.SpIpNavigation).WithMany(p => p.Chitiethoadons)
                .HasForeignKey(d => d.SpIp)
                .HasConstraintName("FK_CHITIETHOADON_SANPHAM");
        });

        modelBuilder.Entity<Danhmuc>(entity =>
        {
            entity.HasKey(e => e.DmId);

            entity.ToTable("DANHMUC");

            entity.Property(e => e.DmId).HasColumnName("DM_ID");
            entity.Property(e => e.MotaDanhmuc)
                .HasMaxLength(10)
                .IsFixedLength()
                .HasColumnName("MOTA_DANHMUC");
            entity.Property(e => e.TenDm)
                .HasMaxLength(100)
                .HasColumnName("TEN_DM");
        });

        modelBuilder.Entity<Hoadon>(entity =>
        {
            entity.HasKey(e => e.HdId);

            entity.ToTable("HOADON");

            entity.Property(e => e.HdId).HasColumnName("HD_ID");
            entity.Property(e => e.Account)
                .HasMaxLength(50)
                .IsFixedLength();
            entity.Property(e => e.CachThucThanhToan).HasColumnName("CACH_THUC_THANH_TOAN");
            entity.Property(e => e.DaThanhToan).HasColumnName("DA_THANH_TOAN");
            entity.Property(e => e.NgayDatHang)
                .HasColumnType("datetime")
                .HasColumnName("NGAY_DAT_HANG");
            entity.Property(e => e.NgayGiaoHang)
                .HasColumnType("datetime")
                .HasColumnName("NGAY_GIAO_HANG");
            entity.Property(e => e.NgayThanhToan)
                .HasColumnType("datetime")
                .HasColumnName("NGAY_THANH_TOAN");
            entity.Property(e => e.Note).HasColumnName("note");
            entity.Property(e => e.SoBan).HasColumnName("SO_BAN");
            entity.Property(e => e.TrangthaiDon).HasColumnName("TRANGTHAI_DON");

            entity.HasOne(d => d.AccountNavigation).WithMany(p => p.Hoadons)
                .HasForeignKey(d => d.Account)
                .HasConstraintName("FK_HOADON_QUANLI");
        });

        modelBuilder.Entity<Quanli>(entity =>
        {
            entity.HasKey(e => e.Account).HasName("PK_QUANLI_1");

            entity.ToTable("QUANLI");

            entity.Property(e => e.Account)
                .HasMaxLength(50)
                .IsFixedLength()
                .HasColumnName("ACCOUNT");
            entity.Property(e => e.Gioitinh).HasColumnName("GIOITINH");
            entity.Property(e => e.Hoten)
                .HasMaxLength(50)
                .HasColumnName("HOTEN");
            entity.Property(e => e.IsQuanLi).HasColumnName("isQuanLi");
            entity.Property(e => e.Ngaysinh).HasColumnName("NGAYSINH");
            entity.Property(e => e.QlPassword)
                .HasMaxLength(50)
                .IsFixedLength()
                .HasColumnName("QL_PASSWORD");
            entity.Property(e => e.TenLienlac)
                .HasMaxLength(50)
                .HasColumnName("TEN_LIENLAC");
        });

        modelBuilder.Entity<Sanpham>(entity =>
        {
            entity.HasKey(e => e.SpId);

            entity.ToTable("SANPHAM");

            entity.Property(e => e.SpId).HasColumnName("SP_ID");
            entity.Property(e => e.DmId).HasColumnName("DM_ID");
            entity.Property(e => e.Gia).HasColumnName("GIA");
            entity.Property(e => e.HinhanhSp).HasColumnName("HINHANH_SP");
            entity.Property(e => e.SoLuongTonKho).HasColumnName("SO_LUONG_TON_KHO");
            entity.Property(e => e.SpBanchay).HasColumnName("SP_BANCHAY");
            entity.Property(e => e.Tensp)
                .HasMaxLength(50)
                .HasColumnName("TENSP");
            entity.Property(e => e.TrangthaiSp).HasColumnName("TRANGTHAI_SP");

            entity.HasOne(d => d.Dm).WithMany(p => p.Sanphams)
                .HasForeignKey(d => d.DmId)
                .HasConstraintName("FK_SANPHAM_DANHMUC");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);

    public string DataTableToJSONWithJSONNet(DataTable table)
    {
        string JSONString = string.Empty;
        JSONString = JsonConvert.SerializeObject(table);
        return JSONString;
    }

    public DataTable ExecuteQuery(string sql)
    {
        DataTable result = new DataTable();

        using (SqlConnection conn = new SqlConnection(ConnString))
        {
            using (SqlCommand cmd = new SqlCommand(sql, conn))
            {
                using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
                {
                    adapter.Fill(result);
                }
            }
        }
        return result;
    }

    public void ExecuteNonQuery(string sql)
    {
        using (SqlConnection conn = new SqlConnection(ConnString))
        {
            using (SqlCommand cmd = new SqlCommand(sql, conn))
            {
                if (conn.State == ConnectionState.Closed)
                {
                    conn.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }
    }


    public DataTable GetSanPham()
    {
        string sql = @"SELECT SP_ID
                            ,TENSP
                            ,GIA
                            ,DM_ID
                            ,SP_BANCHAY
                            ,SO_LUONG_TON_KHO
                            ,TRANGTHAI_SP
                        FROM SANPHAM";
        return ExecuteQuery(sql);
    }

    public void AddSanPham(Sanpham sanpham)
    {
        string sql = $@"INSERT INTO SANPHAM VALUES
                                                    (N'{sanpham.Tensp}',
                                                    {sanpham.Gia},
                                                    null,
                                                    {sanpham.DmId},
                                                    {(sanpham.SpBanchay == true ? 1 : 0)},
                                                    {sanpham.SoLuongTonKho},
                                                    {(sanpham.TrangthaiSp == true ? 1 : 0)})";
        ExecuteNonQuery(sql);
    }
    
    public void DeleteSanPham(int id)
    {
        string sql = @$"update SANPHAM set TRANGTHAI_SP = 0 WHERE SP_ID = {id}";
        ExecuteNonQuery(sql);
    }

    public void UpdateSanPham(Sanpham sanpham)
    {
        string sql = $@"UPDATE [dbo].[SANPHAM]
                       SET TENSP = N'{sanpham.Tensp}'
                          ,GIA = {sanpham.Gia}
                          ,DM_ID = {sanpham.DmId}
                          ,SP_BANCHAY = {(sanpham.SpBanchay == true ? 1 : 0)}
                          ,SO_LUONG_TON_KHO = {sanpham.SoLuongTonKho}
                          ,TRANGTHAI_SP = {(sanpham.TrangthaiSp == true ? 1 : 0)}
                       Where SP_ID = {sanpham.SpId}";
        ExecuteNonQuery(sql);
    }
}
