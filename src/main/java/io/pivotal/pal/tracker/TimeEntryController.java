package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("time-entries")
public class TimeEntryController {
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository =  timeEntryRepository;
    }

    private TimeEntryRepository timeEntryRepository;

    @PostMapping
        public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        ResponseEntity responseEntity = new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
        return responseEntity;

    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        ResponseEntity<TimeEntry> responseEntity ;
        if(timeEntry != null){
            responseEntity = new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity responseEntity =  new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        ResponseEntity<TimeEntry> responseEntity ;
        if(timeEntry != null){
            responseEntity = new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }

        return responseEntity;

    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        ResponseEntity responseEntity =  new ResponseEntity(HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
