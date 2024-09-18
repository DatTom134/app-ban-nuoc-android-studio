using Api.Models;
using Microsoft.AspNetCore.Mvc;
using System.Data;

namespace Api.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ApiAppBanNuoc : Controller
    {
        QuanLyQuanNuocContext context;
        public ApiAppBanNuoc()
        {
            context = new QuanLyQuanNuocContext();
        }
        [HttpGet]
        public void GetAll()
        {
            DataTable sanphams = context.GetSanPham();
            string json = context.DataTableToJSONWithJSONNet(sanphams);
            Response.Clear();
            Response.ContentType = "application/json; charset=utf-8";
            Response.WriteAsync(json);
        }

        [HttpPost("Them")]
        public void Create(string tensp, int gia, int dm_id, bool spbanchay, int soluongtonkho, bool trangthaisp)
        {
            Sanpham sanpham = new Sanpham(tensp, gia, dm_id, spbanchay, soluongtonkho, trangthaisp);
            context.AddSanPham(sanpham);
            DataTable sanphams = context.GetSanPham();
            string json = context.DataTableToJSONWithJSONNet(sanphams);
            Response.Clear();
            Response.ContentType = "application/json; charset=utf-8";
            Response.WriteAsync(json);
        }
        [HttpPost("Xoa")]
        public void Delete(int id)
        {
            context.DeleteSanPham(id);
            DataTable sanpham = context.GetSanPham();
            string json = context.DataTableToJSONWithJSONNet(sanpham);
            Response.Clear();
            Response.ContentType = "application/json; charset=utf-8";
            Response.WriteAsync(json);
        }

        [HttpPost("Update/{id}&{tensp}&{gia}&{dm_id}&{spbanchay}&{soluongtonkho}&{trangthaisp}")]
        public void Update(int id, string tensp, int gia, int dm_id, bool spbanchay, int soluongtonkho, bool trangthaisp)
        {
            Sanpham sanpham = new Sanpham(id, tensp, gia, dm_id, spbanchay, soluongtonkho, trangthaisp);
            context.UpdateSanPham(sanpham);
            DataTable sanphams = context.GetSanPham();
            string json = context.DataTableToJSONWithJSONNet(sanphams);
            Response.Clear();
            Response.ContentType = "application/json; charset=utf-8";
            Response.WriteAsync(json);
        }
    }
}
