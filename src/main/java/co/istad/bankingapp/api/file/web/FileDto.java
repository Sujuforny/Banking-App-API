package co.istad.bankingapp.api.file.web;

import lombok.Builder;

@Builder
public record FileDto(String name,
                      String url,
                      String urlDownload,
                      String extension,
                      long size
                       ) {
}
