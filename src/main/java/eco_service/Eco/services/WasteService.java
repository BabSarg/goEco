package eco_service.Eco.services;

import eco_service.Eco.dtos.WasteAddDTO;
import eco_service.Eco.dtos.WasteDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.filter.WasteFilter;
import eco_service.Eco.response.Response;

import java.util.List;

public interface WasteService {

    Response<ErrorResponse, List<WasteDTO>> getAll(WasteFilter wasteFilter);

    Response<ErrorResponse, WasteDTO> getById(Long id);

    Response<ErrorResponse, List<WasteDTO>> getByEcoServiceId(Long id);

    Response<ErrorResponse, WasteAddDTO> add(WasteAddDTO wasteDTO);
}
