package com.tuan.dictionary.partofspeech;

import com.tuan.dictionary.base.BaseService;
import com.tuan.dictionary.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PartOfSpeechServiceImpl extends BaseServiceImpl<PartOfSpeech,String> implements PartOfSpeechService {
    private PartOfSpeechRepository partOfSpeechRepository;

    @Autowired
    public PartOfSpeechServiceImpl(PartOfSpeechRepository partOfSpeechRepository) {
        this.partOfSpeechRepository = partOfSpeechRepository;
    }

    @Override
    protected CrudRepository<PartOfSpeech, String> getRepository() {
        return partOfSpeechRepository;
    }
}
