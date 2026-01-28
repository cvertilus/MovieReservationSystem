package com.MovieReservationSystem.movieService.service;

import com.MovieReservationSystem.movieService.dto.AuditoriumRequest;
import com.MovieReservationSystem.movieService.dto.AuditoriumResponse;
import com.MovieReservationSystem.movieService.dto.DtoMapper;
import com.MovieReservationSystem.movieService.model.Auditorium;
import com.MovieReservationSystem.movieService.repository.AuditoriumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final  DtoMapper mapper;


   public AuditoriumResponse getAuditoriumById(Long id) {
       Auditorium auditorium =  auditoriumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Auditorium no encontrado"+ id));
       return  mapper.toAuditoriumResponse(auditorium);
   }

   public Auditorium getAuditoriumEntityById(Long id) {
       return auditoriumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Auditorium no encontrado"+ id));
   }

   public AuditoriumResponse saveAuditorium (AuditoriumRequest auditoriumRequest){
       Auditorium auditorium = Auditorium.builder()
                .name(auditoriumRequest.getName())
               .capacity(auditoriumRequest.getCapacity())
                .build();
       Auditorium auditorium1 = auditoriumRepository.save(auditorium);
         return mapper.toAuditoriumResponse(auditorium1);
   }

   public void deleteAuditorium (Long id){
       Auditorium auditorium = auditoriumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Auditorium no encontrado"+ id));
       auditoriumRepository.delete(auditorium);
   }

   public List<AuditoriumResponse> getAllAuditorium(){
         List<Auditorium> auditoriums = auditoriumRepository.findAll();
         return auditoriums.stream()
                .map(mapper::toAuditoriumResponse)
                .toList();
   }

}
