# Smart_Library_Plus - Akıllı Kütüphane Sistemi (ORM Tabanlı)

Bu proje, Nesneye Dayalı Programlama 2 dersi kapsamındaki "ORM Tabanlı Akıllı Kütüphane Sistemi" ödevi için geliştirilmiştir. Projede JDBC yerine Hibernate framework'ü kullanılarak veritabanı işlemleri yönetilmiştir.

## Proje Hakkında
Uygulama, konsol üzerinden çalışan bir kütüphane yönetim sistemidir. Kitap ve öğrenci kayıtları tutulmakta, ödünç alma ve iade işlemleri yapılabilmektedir.

## Kullanılan Teknolojiler ve Yapı
* **Programlama Dili:** Java (JDK 17+)
* **Veritabanı:** SQLite
* **ORM Aracı:** Hibernate 6
* **Proje Yöneticisi:** Maven
* **Mimari:** DAO (Data Access Object) Tasarım Deseni kullanıldı.

## Yerine Getirilen Şartlar
1.  **Entity İlişkileri:**
    * Student - Loan arasında `OneToMany` ilişkisi kuruldu.
    * Loan - Book arasında `OneToOne` ilişkisi kuruldu.
2.  **Veritabanı Yönetimi:**
    * Tablolar `hbm2ddl.auto=update` ayarı ile Hibernate tarafından otomatik oluşturulmaktadır.
    * SQL sorguları yazılmadan, Hibernate metodları (persist, merge, remove) kullanıldı.
3.  **Proje Klasör Yapısı:**
    * `entity`, `dao`, `util` ve `app` paketlerine ayrılarak modüler yapı sağlandı.

## Kurulum ve Çalıştırma
1.  Proje klasörünü IntelliJ IDEA ile açın.
2.  Maven bağımlılıklarının (pom.xml) yüklenmesini bekleyin.
3.  `src/main/java/tr/com/smartlib/app/Main.java` dosyasını çalıştırın.
4.  Veritabanı dosyasının (`library.db`) proje ana dizininde otomatik oluştuğu görülecektir.

---

Hazırlayan: [Muhammed Can Yilmaz] Numara: [20230108032] Ders: Nesneye Dayalı Programlama 2
