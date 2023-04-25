package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.TurfRequestDto;
import com.basics.turfbooking.dto.TurfResponseDto;
import com.basics.turfbooking.exceptions.NotFoundException;

import java.util.List;

public interface TurfService {

    TurfResponseDto addTurf(TurfRequestDto turfRequestDto);

    List<TurfResponseDto> getAllTurf(Integer pageNo,Integer pageSize);

    TurfResponseDto updateTurf(TurfRequestDto turfRequestDto,Integer id) throws NotFoundException;

    TurfResponseDto getTurfById(Integer id) throws NotFoundException;

    String deleteTurf(Integer id) throws NotFoundException;

}
