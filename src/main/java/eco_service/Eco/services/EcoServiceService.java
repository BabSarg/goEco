package eco_service.Eco.services;

import eco_service.Eco.dtos.ChangePasswordEcoServiceDto;
import eco_service.Eco.dtos.EcoServiceAddDTO;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.filter.EcoServiceFilter;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.response.Response;

import java.util.List;

public interface EcoServiceService {

    Response<ErrorResponse, List<EcoServiceDTO>> getAll(EcoServiceFilter ecoServiceFilter);

    Response<ErrorResponse, EcoServiceDTO> getById(Long id);

    Response<ErrorResponse, EcoServiceDTO> add(EcoServiceAddDTO ecoServiceAddDTO);

    Response<ErrorResponse, EcoServiceDTO> update(Long id, EcoServiceDTO ecoServiceDTO);

    Response<ErrorResponse, EcoServiceDTO> changePassword(Long id, ChangePasswordEcoServiceDto ecoServiceDto);

    void delete(Long id);
}
