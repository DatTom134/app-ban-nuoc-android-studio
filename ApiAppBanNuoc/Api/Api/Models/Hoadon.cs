using System;
using System.Collections.Generic;

namespace Api.Models;

public partial class Hoadon
{
    public int HdId { get; set; }

    public string? Account { get; set; }

    public int? SoBan { get; set; }

    public DateTime? NgayDatHang { get; set; }

    public DateTime? NgayGiaoHang { get; set; }

    public bool? DaThanhToan { get; set; }

    public DateTime? NgayThanhToan { get; set; }

    public int? CachThucThanhToan { get; set; }

    public string? Note { get; set; }

    public bool? TrangthaiDon { get; set; }

    public virtual Quanli? AccountNavigation { get; set; }

    public virtual ICollection<Chitiethoadon> Chitiethoadons { get; set; } = new List<Chitiethoadon>();
}
