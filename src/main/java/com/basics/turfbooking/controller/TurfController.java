package com.basics.turfbooking.controller;

import com.basics.turfbooking.dto.TurfRequestDto;
import com.basics.turfbooking.dto.TurfResponseDto;
import com.basics.turfbooking.exceptions.ResourceNotFoundException;
import com.basics.turfbooking.service.TurfService;
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
    public TurfResponseDto getTurfById(@PathVariable Integer id) throws ResourceNotFoundException {
        return turfService.getTurfById(id);
    }

    @GetMapping("/getTurfByLocation/{location}")
    public List<TurfResponseDto> getTurfByLocation(@PathVariable String location){
        return turfService.getTurfByLocation(location);
    }
    @PutMapping("/updateTurfById/{id}")
    public TurfResponseDto updateTurfById(@PathVariable Integer id,@RequestBody TurfRequestDto turfRequestDto)throws ResourceNotFoundException{
        return turfService.updateTurf(turfRequestDto, id);
    }

    @DeleteMapping("/deleteMapping/{id}")
    public String deleteTurfById(@PathVariable Integer id)throws ResourceNotFoundException{
        return turfService.deleteTurf(id);
    }

}
