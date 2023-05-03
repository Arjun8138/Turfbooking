package com.basics.turfbooking.service;

import com.basics.turfbooking.dto.TurfRequestDto;
import com.basics.turfbooking.dto.TurfResponseDto;
import com.basics.turfbooking.entity.Turf;
import com.basics.turfbooking.exceptions.ResourceNotFoundException;
import com.basics.turfbooking.repository.TurfRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurfServiceImpl implements TurfService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TurfRepository turfRepository;
    @Override
    public TurfResponseDto addTurf(TurfRequestDto turfRequestDto) {

        Turf turf = new Turf();
        turf.setName(turfRequestDto.getName());
        turf.setClosingTime(turfRequestDto.getClosingTime());
        turf.setOpeningTime(turfRequestDto.getOpeningTime());
        turf.setLocation(turfRequestDto.getLocation());
        turf.setLicenseNumber(turfRequestDto.getLicenseNumber());
        turf.setOwnerName(turfRequestDto.getOwnerName());
        turf.setRate(turfRequestDto.getRate());
        turfRepository.save(turf);

        TurfResponseDto turfResponseDto=new TurfResponseDto();
        turfResponseDto.setName(turf.getName());
        turfResponseDto.setClosingTime(turf.getClosingTime());
        turfResponseDto.setOpeningTime(turf.getOpeningTime());
        turfResponseDto.setLocation(turf.getLocation());
        turfResponseDto.setRate(turf.getRate());
        return turfResponseDto;
    }

    @Override
    public List<TurfResponseDto> getAllTurf(Integer pageNo, Integer pageSize) {


        PageRequest pageRequest = PageRequest.of(pageNo,pageSize);
        Page<Turf> page = turfRepository.findAll(pageRequest);
        List<Turf> turfList = page.getContent();

        List<TurfResponseDto> turfResponseDtoList = turfList.stream().
                map(turf->modelMapper.map(turf,TurfResponseDto.class)).
                collect(Collectors.toList());
        return turfResponseDtoList;
    }

    @Override
    public TurfResponseDto updateTurf(TurfRequestDto turfRequestDto, Integer id) throws ResourceNotFoundException {

        Turf turf= turfRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Turf","id",id));
        turf.setName(turfRequestDto.getName());
        turf.setLocation(turfRequestDto.getLocation());
        turf.setOpeningTime(turfRequestDto.getOpeningTime());
        turf.setClosingTime(turfRequestDto.getClosingTime());
        turf.setRate(turfRequestDto.getRate());
        turf.setOwnerName(turfRequestDto.getOwnerName());
        turf.setLicenseNumber(turfRequestDto.getLicenseNumber());
        turfRepository.save(turf);

        TurfResponseDto turfResponseDto = new TurfResponseDto();
        turfResponseDto.setLocation(turf.getLocation());
        turfResponseDto.setName(turf.getName());
        turfResponseDto.setRate(turf.getRate());
        turfResponseDto.setOpeningTime(turf.getOpeningTime());
        turfResponseDto.setClosingTime(turf.getClosingTime());
        return turfResponseDto;
    }

    @Override
    public TurfResponseDto getTurfById(Integer id) throws ResourceNotFoundException {

        Turf turf =
        turfRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Turf","id",id));
        TurfResponseDto turfResponseDto=new TurfResponseDto();
        turfResponseDto.setRate(turf.getRate());
        turfResponseDto.setName(turf.getName());
        turfResponseDto.setOpeningTime(turf.getOpeningTime());
        turfResponseDto.setClosingTime(turf.getClosingTime());
        turfResponseDto.setLocation(turf.getLocation());
        return turfResponseDto;
    }

    @Override
    public List<TurfResponseDto> getTurfByLocation(String location) {

        List<Turf> turfList = turfRepository.findTurfByLocation(location);
        List<TurfResponseDto>turfResponseDtoList=turfList.stream()
                .map(turf -> modelMapper.map(turf,TurfResponseDto.class)).collect(Collectors.toList());
        if (turfResponseDtoList==null){

        }
        return turfResponseDtoList;
    }

    @Override
    public String deleteTurf(Integer id) throws ResourceNotFoundException {

        Turf turf = turfRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Turf","id",id));
        turfRepository.delete(turf);
        return "Turf successfully deleted";
    }
}
