using System;
using System.Collections.Generic;

namespace Api.Models;

public partial class Quanli
{
    public string Account { get; set; } = null!;

    public string QlPassword { get; set; } = null!;

    public string? Hoten { get; set; }

    public string TenLienlac { get; set; } = null!;

    public DateOnly? Ngaysinh { get; set; }

    public int? Gioitinh { get; set; }

    public bool IsQuanLi { get; set; }

    public virtual ICollection<Hoadon> Hoadons { get; set; } = new List<Hoadon>();
}
