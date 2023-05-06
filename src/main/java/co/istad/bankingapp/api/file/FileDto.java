package co.istad.bankingapp.api.file;

import lombok.Builder;

@Builder
public record FileDto(String name,
                      String url,
                      String extension,
                      long size
                       ) {
}
