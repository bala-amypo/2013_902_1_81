package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;

import java.util.List;

public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset updateStatus(Long id, String status) {
        Asset asset = getAsset(id);
        asset.setStatus(status);
        return assetRepository.save(asset);
    }
}
