package kodlama.io.rentACar.dataAccess.abstracts;




import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	// JpaRepository bir interfacedir.
	// Tipini ve bu tipe karşılık gelen veritabanındaki tablonun primary keyinin veri tipini belirttik
	
	boolean existsByName(String name); // burada jpa bizim için özel bir sorgu üretiyor . exists ve name kelimelerinden bir sorgu üretiliyor.
	// daha fazlası için spring jpa keywords araştır.
}
