package com.phonecontacts.controller;

import com.phonecontacts.dto.contact.ContactRequestDto;
import com.phonecontacts.dto.contact.ContactResponseDto;
import com.phonecontacts.dto.contact.ContactUpdateReqDto;
import com.phonecontacts.dto.contact.ContactUpdateResDto;
import com.phonecontacts.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(@RequestBody ContactRequestDto requestDto) {
        log.trace("Create contact body {}", requestDto);
        return new ResponseEntity<>(contactService.createContact(requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUpdateResDto> updateContact(@PathVariable Long id,
                                                             @RequestBody ContactUpdateReqDto reqDto,
                                                             @RequestHeader(value = "Authorization") String token) {
        log.trace("Update contact by id {} body {}", id, reqDto);
        return new ResponseEntity<>(contactService.updateContact(id, reqDto, token), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContactResponseDto>> getAllContacts() {
        log.trace("Get all contacts");
        return new ResponseEntity<>(contactService.getAllContact(), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        log.trace("Delete contact by id {}", id);
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
