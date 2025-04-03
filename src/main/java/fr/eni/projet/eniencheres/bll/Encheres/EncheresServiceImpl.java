package fr.eni.projet.eniencheres.bll.Encheres;

import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.dal.Encheres.EncheresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncheresServiceImpl implements EncheresService {
    private EncheresRepository articlesAVendreRepository;

    public EncheresServiceImpl(EncheresRepository articlesAVendreRepository) {
        super();
        this.articlesAVendreRepository = articlesAVendreRepository;
    }

    @Override
    public List<Enchere> consulterArticlesAVendre() {
        return articlesAVendreRepository.read();
    }

    @Override
    public List<Enchere> consulterArticlesAVendre(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo) {
        return articlesAVendreRepository.read(articleName, category, isAchat, achatSelect, venteSelect, pseudo);
    }
}
