using System;
using System.Collections.Generic;

namespace Api.Models;

public partial class Chitiethoadon
{
    public int CthdId { get; set; }

    public int? HdId { get; set; }

    public int? SpIp { get; set; }

    public int? Tongtien { get; set; }

    public int? Soluong { get; set; }

    public virtual Hoadon? Hd { get; set; }

    public virtual Sanpham? SpIpNavigation { get; set; }
}
