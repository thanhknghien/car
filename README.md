 BÁO CÁO ĐỒ ÁN MÔN PHƯƠNG PHÁP LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG
                            NHÓM 5. 
                            TÊN ĐỀ TÀI: ỨNG DỤNG QUẢN LÝ BÁN XE HƠI.

Thành viên: Võ Lâm Gia Kiệt, MSSV: 3123410183.
            Đinh Phúc Thành, MSSV: 3123410333.

I.Tổng quan về ứng dụng:
- Ứng dụng quản lí bán xe hơi chạy trên màn hình console có các chức năng cơ bản:
+ Người dùng(User):
    # Đăng nhập
    # Đăng kí với người dùng mới
    # Xem sản phẩm, Tìm kiếm sản phẩm theo: tên, id, hãng sản xuất, năm sản xuất, theo khoảng giá, theo loại xe.
    # Xem giỏ hàng: Sửa, thêm mới, xóa sản phẩm. Đặt hàng - Cọc tiền. Xem trạng thái đơn hàng- xem lịch sử các đơn hàng đã đặt.
    # Xem thông tin cá nhân(tên, địa chỉ, ID, email), sửa thông tin cá nhân, thay đổi mật khẩu.
    # Xem hộp thư khách hàng(mailbox): xem thư, tạo thư mới, gửi thư.
+ Admin:
    # Đăng nhập.
    # Các thao tác quản lý sản phẩm(Thêm, sửa, xóa, lưu vào file, đọc dữ liệu từ file, tìm kiếm). 
    # Các thao tác quản lý người dùng(Thêm, sửa, xóa, lưu vào file, đọc dữ liệu từ file,tìm kiếm).
    # Các thao tác quản lý đơn hàng(Thêm, sửa, xóa, lưu vào file, đọc dữ liệu từ file, tìm kiếm, chỉnh sửa trạng thái, xem chi tiết).
    # Các thao tác quản lý hộp thư(xem phản hồi của khách hàng, gửi thư).
    # Các chức năng thống kê cơ bản(
        - Thống kê các đơn hàng theo trạng thái.
        - Thống kê tổng số lượng xe đã bán.
        - Thống kê người dùng có nhiều đơn hàng nhất.
    )
II.Thiết kế Class:
    - InterFace: Modify{
        method: add, edit, delete, import, export
    }
    -Car{
        ( id: int,
          name: String,
          brand: String,
          yearOfProduction,
          price: double,
          category: String,
          warranty: Warranty)
    }
    - ElectricCar extends Car{
        (
            batteryCapacity: int (kWh),
            range: int (km)
       )
    }
    - GasolineCar extends Car{
        (
            fuelConsumption: int (l/km),
            tankCapacity: int (l)
        )
    }
    - Warranty{
        (
            conditions: String,
            warrantyPeriod: int //static,
            warrantyType: String
        )
    }
    - CarManagerment implements Modify<Car>

    - User{
        (
            id: int,
            username: String,
            password: String,
            email: String,
            phone: String,
            cart: Cart,
            mailBox: mailBox
        )
    }
    - CartItem{
        (
            car: Car,
            quantity: int
        )
    }
    - Cart{
        (
            items: List<CartItem>
        )
    }
    - OderDetail{
        (
            car: Car,
            quantity: int,
            price: double
        )
    }
    - Oder{
        (
            oderID: String,
            customer: Customer,
            oderDetail:List <OrderDetail>,
            total: double,
            payment: localDateTime,
            status: String
        )
    }
    - OderManagerment implements Modify<Oder>
    - Admin{
        (
            username(static): String (admin),
            password(static): String (admin123)
        )
    }
    - MailBox{
        (
            content: String,
            sentTime: localDateTime,
            response: String,
            responseTime: localDateTime,
            customerName: String
        )
    }
    - MailBoxManagerment implements Modify<MailBox>
    Statistical{
        (
            oder: Oder,
            customer: Customer
        )
    }