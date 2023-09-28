package kodlama.io.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import lombok.AllArgsConstructor;

@RestController //annotation bu sınıfın controller olduğunu belirttik
@RequestMapping("/api/brands")  
@AllArgsConstructor
public class BrandsController {
	private BrandService brandService;

	@GetMapping() // bu metod getAll ismi ile çağırılabilir.
	public List<GetAllBrandsResponse> getAll(){
		
		return brandService.getAll();
	}
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED) // bu kısmı yazarsak post işlemi 201 döndürür	
	public void add(@RequestBody CreateBrandRequest createBrandRequest){
		
		this.brandService.add(createBrandRequest);
	}
	
	@GetMapping("/{id}")
	public GetByIdBrandResponse getById(@PathVariable int id) {		
		return this.brandService.getById(id);
	}
	
	@PutMapping
	public void update(@RequestBody() UpdateBrandRequest updateBrandRequest) {
		this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.brandService.delete(id);
	}
	
}
