package tr.com.smartlib.app;

import tr.com.smartlib.dao.*;
import tr.com.smartlib.entity.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookDao bookDao = new BookDao();
    private static final StudentDao studentDao = new StudentDao();
    private static final LoanDao loanDao = new LoanDao();

    public static void main(String[] args) {
        // Hibernate loglarını sustur
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        while (true) {
            System.out.println("\n=== 🏛️ SMART LIBRARY ANA MENÜ ===");
            System.out.println("1 - 📚 Kitap İşlemleri");
            System.out.println("2 - 🎓 Öğrenci İşlemleri");
            System.out.println("3 - 🤝 Ödünç/İade İşlemleri");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = okuInt();

            if (secim == 0) {
                System.out.println("Program kapatılıyor... İyi günler!");
                break;
            }

            switch (secim) {
                case 1: kitapIslemleri(); break;
                case 2: ogrenciIslemleri(); break;
                case 3: oduncIslemleri(); break;
                default: System.out.println("❌ Geçersiz seçim!");
            }
        }
    }

    // --- ÖĞRENCİ İŞLEMLERİ ---
    private static void ogrenciIslemleri() {
        while (true) {
            System.out.println("\n--- 🎓 ÖĞRENCİ İŞLEMLERİ ---");
            System.out.println("1 - Öğrenci Ekle");
            System.out.println("2 - Öğrenci Sil");
            System.out.println("3 - Öğrencileri Listele");
            System.out.println("0 - Ana Menüye Dön");
            System.out.print("Seçim: ");

            int secim = okuInt();
            if (secim == 0) break;

            try {
                switch (secim) {
                    case 1:
                        System.out.print("Öğrenci Adı: "); String name = scanner.nextLine();
                        System.out.print("Bölüm: "); String dept = scanner.nextLine();
                        studentDao.save(new Student(name, dept));
                        System.out.println("✅ Öğrenci eklendi.");
                        break;
                    case 2:
                        // ÖNCE LİSTEYİ GÖSTERİYORUZ (KOLAYLIK OLSUN DİYE)
                        System.out.println("\n--- SİLİNECEK ÖĞRENCİYİ SEÇİNİZ ---");
                        studentDao.getAll().forEach(System.out::println);
                        System.out.println("-----------------------------------");

                        System.out.print("👉 Silinecek Öğrenci ID: ");
                        Long delId = scanner.nextLong(); scanner.nextLine();
                        studentDao.delete(delId);
                        break;
                    case 3:
                        System.out.println("\n--- ÖĞRENCİ LİSTESİ ---");
                        studentDao.getAll().forEach(System.out::println);
                        break;
                    default: System.out.println("Geçersiz seçim.");
                }
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    // --- KİTAP İŞLEMLERİ ---
    private static void kitapIslemleri() {
        while (true) {
            System.out.println("\n--- 📚 KİTAP İŞLEMLERİ ---");
            System.out.println("1 - Kitap Ekle");
            System.out.println("2 - Kitap Sil");
            System.out.println("3 - Kitapları Listele");
            System.out.println("0 - Ana Menüye Dön");
            System.out.print("Seçim: ");

            int secim = okuInt();
            if (secim == 0) break;

            try {
                switch (secim) {
                    case 1:
                        System.out.print("Kitap Başlığı: "); String title = scanner.nextLine();
                        System.out.print("Yazar: "); String author = scanner.nextLine();
                        System.out.print("Yıl: "); int year = scanner.nextInt(); scanner.nextLine();
                        bookDao.save(new Book(title, author, year));
                        System.out.println("✅ Kitap eklendi.");
                        break;
                    case 2:
                        // ÖNCE LİSTEYİ GÖSTERİYORUZ
                        System.out.println("\n--- SİLİNECEK KİTABI SEÇİNİZ ---");
                        bookDao.getAll().forEach(System.out::println);
                        System.out.println("--------------------------------");

                        System.out.print("👉 Silinecek Kitap ID: ");
                        Long delId = scanner.nextLong(); scanner.nextLine();
                        bookDao.delete(delId);
                        break;
                    case 3:
                        System.out.println("\n--- KİTAP LİSTESİ ---");
                        bookDao.getAll().forEach(System.out::println);
                        break;
                    default: System.out.println("Geçersiz seçim.");
                }
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    // --- ÖDÜNÇ İŞLEMLERİ ---
    private static void oduncIslemleri() {
        while (true) {
            System.out.println("\n--- 🤝 ÖDÜNÇ İŞLEMLERİ ---");
            System.out.println("1 - Kitap Ver (Ödünç)");
            System.out.println("2 - Kitap Al (İade)");
            System.out.println("3 - Listeyi Gör");
            System.out.println("0 - Ana Menüye Dön");
            System.out.print("Seçim: ");

            int secim = okuInt();
            if (secim == 0) break;

            try {
                switch (secim) {
                    case 1:
                        // 1. ADIM: ÖĞRENCİ SEÇTİR
                        System.out.println("\n--- 1. ADIM: ÖĞRENCİ SEÇİNİZ ---");
                        studentDao.getAll().forEach(System.out::println);
                        System.out.print("👉 Öğrenci ID: ");
                        Long sId = scanner.nextLong();

                        // 2. ADIM: KİTAP SEÇTİR
                        System.out.println("\n--- 2. ADIM: KİTAP SEÇİNİZ ---");
                        bookDao.getAll().forEach(System.out::println);
                        System.out.print("👉 Kitap ID: ");
                        Long bId = scanner.nextLong(); scanner.nextLine();

                        Student s = studentDao.getById(sId);
                        Book b = bookDao.getById(bId);

                        if (s != null && b != null) {
                            if ("BORROWED".equals(b.getStatus())) {
                                System.out.println("⛔ HATA: Bu kitap zaten '" + b.getTitle() + "' başkasında!");
                            } else {
                                Loan loan = new Loan(s, b);
                                loanDao.save(loan);
                                b.setStatus("BORROWED");
                                bookDao.update(b);
                                System.out.println("✅ " + b.getTitle() + " kitabı, " + s.getName() + " adlı öğrenciye verildi.");
                            }
                        } else {
                            System.out.println("❌ Hata: Girilen ID'ler hatalı.");
                        }
                        break;

                    case 2:
                        // İADE EDİLECEK KİTAPLARI GÖSTERMEK GÜZEL OLURDU AMA
                        // ŞİMDİLİK TÜM ÖDÜNÇ LİSTESİNİ GÖSTERELİM
                        System.out.println("\n--- İADE EDİLECEK KİTABI SEÇİNİZ ---");
                        loanDao.getAll().forEach(System.out::println);
                        System.out.println("------------------------------------");

                        System.out.print("👉 İade Edilen Kitap ID'si (Book ID): ");
                        Long returnId = scanner.nextLong(); scanner.nextLine();
                        Loan activeLoan = loanDao.getActiveLoanByBook(returnId);

                        if (activeLoan != null) {
                            activeLoan.setReturnDate(LocalDate.now());
                            loanDao.update(activeLoan);
                            Book returningBook = activeLoan.getBook();
                            returningBook.setStatus("AVAILABLE");
                            bookDao.update(returningBook);
                            System.out.println("✅ Kitap iade alındı: " + returningBook.getTitle());
                        } else {
                            System.out.println("❌ Bu kitap şu an kimsede değil veya ID yanlış.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--- AKTİF İŞLEMLER ---");
                        loanDao.getAll().forEach(System.out::println);
                        break;
                    default: System.out.println("Geçersiz seçim.");
                }
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    private static int okuInt() {
        try {
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}