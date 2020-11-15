package com.posjava.redesocial.util;

import com.posjava.redesocial.model.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class Util {

    public static ResponseEntity getResposeError(String message) {
        return new ResponseEntity<>(null, setHeaderMessage(message), HttpStatus.BAD_REQUEST);
    }

    private static HttpHeaders setHeaderMessage(String message) {
        HttpHeaders header = new HttpHeaders();
        header.add("message-error", message);
        return header;
    }

}

