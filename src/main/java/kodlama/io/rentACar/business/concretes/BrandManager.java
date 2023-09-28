package kodlama.io.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service // bu bir business nesnesi
@AllArgsConstructor
public class BrandManager implements BrandService{
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;
	
	@Override
	public List<GetAllBrandsResponse> getAll() {
		// İş kuralları
		List<Brand> brands = brandRepository.findAll();
		/*
		List<GetAllBrandsResponse> brandsResponse = new ArrayList<GetAllBrandsResponse>();
		for (Brand brand : brands) {
			GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
			responseItem.setId(brand.getId());
			responseItem.setName(brand.getName());
			brandsResponse.add(responseItem);
		}*/
		
		List<GetAllBrandsResponse> brandsResponse = brands.stream().
				map(brand-> this.modelMapperService.forResponse(). // bu satırdaki map metodu stream api sindeki map metodu
						map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());  // bu satırdaki map metodu ise modelmapper ın map metodu
		
		// stream elimizdeki listeyi tek tek gezmemizi sağlar
		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		/*
		Brand brand =new Brand();		
		brand.setName(createBrandRequest.getName());
		*/
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());// burası bir exception fırlatırsa alt satırdaki işlemler yapılmayacak
		this.brandBusinessRules.checkIfBrandNameEmpty(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
	}

	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow();// id ye karşılık gelen kayıt yoksa exception fırlatır
		GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);		
		return response;
	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
	}

	@Override
	public void delete(int id) {
		this.brandRepository.deleteById(id);
	}
}
