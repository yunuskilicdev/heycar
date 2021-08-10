package com.kilic.yunus.heycar.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kilic.yunus.heycar.dto.ListingCsvDto;
import com.kilic.yunus.heycar.mapper.ListingMapper;
import com.kilic.yunus.heycar.model.Listing;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Yunus Kilic
 */
@Service
public class CsvService {

    public List<Listing> parseCsvFile(MultipartFile file) {

        FileOutputStream fos;
        try {
            File csvFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            fos = new FileOutputStream(csvFile);
            fos.write(file.getBytes());

            CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.configure(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS, true);
            MappingIterator<ListingCsvDto> listingIterator = csvMapper.readerFor(ListingCsvDto.class).with(orderLineSchema).readValues(csvFile);

            List<ListingCsvDto> listingCsvDto = listingIterator.readAll();
            List<Listing> response = listingCsvDto.stream().map(ListingMapper.INSTANCE::csvToListing).toList();
            fos.close();
            return response;
        } catch (IOException ex) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
