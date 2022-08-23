package br.univille.dsi2022.service;

import org.springframework.web.multipart.MultipartFile;

public interface SalvarArquivosService {
    String save(MultipartFile file);
}
