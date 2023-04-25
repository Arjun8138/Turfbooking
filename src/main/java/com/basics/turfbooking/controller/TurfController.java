package com.basics.turfbooking.controller;

import com.basics.turfbooking.dto.TurfRequestDto;
import com.basics.turfbooking.dto.TurfResponseDto;
import com.basics.turfbooking.exceptions.NotFoundException;
import com.basics.turfbooking.service.TurfService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turfBooking/turf")
public class TurfController {

    @Autowired
    private TurfService turfService;
    @PostMapping("/addTurf")
    public TurfResponseDto addTurf(@RequestBody TurfRequestDto turfRequestDto){
        return turfService.addTurf(turfRequestDto);
    }

    @GetMapping("/getAllTurf")
    public List<TurfResponseDto> getAllTurf(@RequestParam(value = "pageNo",defaultValue = "0",required = false)Integer pageNumber,
                                            @RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize){
        return turfService.getAllTurf(pageNumber,pageSize);
    }

    @GetMapping("/getTurfById/{id}")
    public TurfResponseDto getTurfById(@PathVariable Integer id) throws NotFoundException {

        return turfService.getTurfById(id);
    }
    @PutMapping("/updateTurfById/{id}")
    public TurfResponseDto updateTurfById(@PathVariable Integer id,@RequestBody TurfRequestDto turfRequestDto)throws NotFoundException{
        return turfService.updateTurf(turfRequestDto, id);
    }

    @DeleteMapping("/deleteMapping/{id}")
    public String deleteTurfById(@PathVariable Integer id)throws NotFoundException{
        return turfService.deleteTurf(id);
    }

}
