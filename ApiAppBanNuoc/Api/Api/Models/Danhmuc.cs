using System;
using System.Collections.Generic;

namespace Api.Models;

public partial class Danhmuc
{
    public int DmId { get; set; }

    public string? TenDm { get; set; }

    public string? MotaDanhmuc { get; set; }

    public virtual ICollection<Sanpham> Sanphams { get; set; } = new List<Sanpham>();
}
