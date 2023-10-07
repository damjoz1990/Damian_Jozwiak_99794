package com.example.damian_jozwiak_99794.domain.genre;

import com.example.damian_jozwiak_99794.domain.genre.dto.GenreDto;

class GenreDtoMapper {
    static GenreDto map(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName()
        );
    }
}