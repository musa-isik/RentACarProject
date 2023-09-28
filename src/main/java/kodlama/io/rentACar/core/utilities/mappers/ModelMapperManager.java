package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{

	private ModelMapper modelMapper;
	
	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		/* 
		 Buradaki MatchingStrategies.LOOSE kısmı gelen isteğe karşı
		 döndürülecek olan response içinde her alanın maplenmesine gerek olmadığını belirtir 
		 örneğin veritabanından gelen verinin içinde id name x y z gibi alanlar olsun mapleme yaparken sadece 
		 name ve x alanlarını maplemek istiyorsam loose stratejisini seçmeliyim aksi taktirde hata alırım.	
		*/
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD);
		/* 
		MatchingStrategies.STANDARD seçeneği ise yukarıdaki durumun tam tersindir. Yani kullanıcı veritabanına
		bir kayıt için istek gönderdiğinde verdiği verilerin tamamının veritabanındaki tabloya karşılık gelmesini sağlar
		aksi taktirde hata verir.
		*/
		return this.modelMapper;
	}

}
