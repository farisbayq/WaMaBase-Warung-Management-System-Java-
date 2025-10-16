**WaMaBase — Warung Management System**
**WaMaBase** adalah aplikasi desktop yang membantu pemilik warung dalam mengelola operasional sehari-hari secara efisien.
Aplikasi ini mencakup pelacakan stok barang, koordinasi dengan pemasok, pencatatan transaksi, serta manajemen staf.
Dengan WaMaBase, pemilik warung dapat meminimalkan kerugian akibat kehilangan stok, memantau pemasok dengan lebih terorganisir, dan menyederhanakan pengelolaan usaha.

**Fitur Utama**
- **Login System** — autentikasi pengguna untuk keamanan data.
- **Home Page** — tampilan utama berisi ringkasan operasional.
- **Inventory Management** — mencatat, menambah, menghapus, dan memperbarui stok barang.
- **Customer Management** — mengelola data pelanggan dan riwayat transaksi.
- **Transaction Management** — mencatat transaksi penjualan dan pembelian secara otomatis.
- **Report Page** — menampilkan laporan operasional, stok, dan transaksi.

**Arsitektur Aplikasi**
**Main.java** — titik masuk aplikasi, berfungsi sebagai penghubung antar halaman (launcher).
**LoginView.java & LoginController.java** — inisialisasi dan logika halaman login.
**HomeView.java & HomeController.java** — inisialisasi dan logika halaman utama.
**InventoryView.java & InventoryController.java** — pengelolaan stok barang.
**CustomerView.java & CustomerController.java** — pengelolaan data pelanggan.
**TransactionView.java & TransactionController.java** — pencatatan transaksi.
**Report.java** — inisialisasi tampilan laporan serta pemrosesan datanya.
**DatabaseConnection.java** — penghubung aplikasi dengan basis data PostgreSQL.

**Struktur Database (PostgreSQL)**
Aplikasi ini menggunakan PostgreSQL sebagai sistem manajemen basis data.
Struktur SQL meliputi tabel untuk:
- Barang / Inventory
- Pelanggan
- Pemasok
- Transaksi (Header & Detail)
- Staf / Pengguna
File skema SQL tersedia di folder **Database_WaMaBase**.

Cara Instalasi dan Menjalankan Aplikasi
1. Persiapan Database
  - Install dan jalankan PostgreSQL.
  - Buat database baru, misalnya wamabase_db.
  - Jalankan skrip SQL dari folder Database_WaMaBase untuk membuat tabel.
2. Konfigurasi Koneksi
  - Pastikan file DatabaseConnection.java berisi konfigurasi yang sesuai:
      String url = "jdbc:postgresql://localhost:5432/wamabase_db";
      String user = "postgres";
      String password = "your_password";
3. Menjalankan Aplikasi
  - Import proyek ke IDE (IntelliJ / Eclipse / NetBeans).
  - Pastikan JavaFX SDK dan PostgreSQL JDBC Driver telah terpasang di classpath.
  - Jalankan Main.java untuk membuka aplikasi.

**Tim Pengembang**
Arya Wira Raja — 2702302984
Avanindra Ikhsan Hafizha — 2702365530
Faris Bayhaqi — 2702378395
Muhamad Fadhil Saputra — 2702365354
Wan Ahmad Ilhamzakky — 2702370202
Bryan Winston Tingginehe — 2702247815
Muhammad Rakhien Althaf — 2702378483

**Lisensi**
Proyek ini dikembangkan untuk keperluan akademik dan pembelajaran.
Distribusi ulang dapat dilakukan dengan mencantumkan atribusi kepada pengembang asli.
