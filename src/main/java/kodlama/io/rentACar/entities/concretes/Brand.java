package kodlama.io.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="brands") // bu kısım bu sınıfı veritabanındaki brands adındaki tabloyla eşleştirir
@Data // getter ve setter'ları arka planda oluşturur sadece getter bloklarının olması için @Getter diyebiliriz.
@AllArgsConstructor // tüm parametrelere atama yapan bir yapıcı method oluşturur
@NoArgsConstructor // parametre almayan bir yapıcı method oluşturur
@Entity	// bu classın bir veritabanı varlığı olduğunu belirtir.	
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//bu field'ın veritabanında hangi sütuna karşılık geldiğini belirttik. Her zaman field adı ile sütun adı aynı olmayabilir. Bu durumda karşılaşılacak problem bu yöntem ile çözülür.
	@Column(name="name")
	private String name;
	
	
	@OneToMany(mappedBy="brand") // model sınıfındaki brand nesnesiyle mapledik
	private List<Model> models;
	
	
}
