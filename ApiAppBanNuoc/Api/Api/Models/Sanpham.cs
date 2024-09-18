using Microsoft.EntityFrameworkCore.Diagnostics;
using Microsoft.Identity.Client;
using System;
using System.Collections.Generic;

namespace Api.Models;

public partial class Sanpham
{
    public int SpId { get; set; }

    public string? Tensp { get; set; }

    public int? Gia { get; set; }

    public string? HinhanhSp { get; set; }

    public int? DmId { get; set; }

    public bool? SpBanchay { get; set; }

    public int? SoLuongTonKho { get; set; }

    public bool? TrangthaiSp { get; set; }

    public virtual ICollection<Chitiethoadon> Chitiethoadons { get; set; } = new List<Chitiethoadon>();

    public virtual Danhmuc? Dm { get; set; }
    public Sanpham(string tensp, int gia, int dm_id, bool spbanchay, int soluongtonkho, bool trangthaisp)
    {
        Tensp = tensp;
        Gia = gia;
        HinhanhSp = null;
        DmId = dm_id;
        SpBanchay = spbanchay;
        SoLuongTonKho = soluongtonkho;
        TrangthaiSp = trangthaisp;
    }

    public Sanpham(int id, string tensp, int gia, int dm_id, bool spbanchay, int soluongtonkho, bool trangthaisp)
    {
        SpId = id;
        Tensp = tensp;
        Gia = gia;
        HinhanhSp = null;   
        DmId = dm_id;
        SpBanchay = spbanchay;
        SoLuongTonKho = soluongtonkho;
        TrangthaiSp = trangthaisp;
    }
}
