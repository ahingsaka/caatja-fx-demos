package com.katspow.caatjafxdemos;

import java.util.HashMap;
import java.util.Map;

import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatjafx.CaatjaFxImage;
import com.katspow.caatjafx.CaatjaFxImageService;

public class ImageLoader implements CaatjaImageLoader {

	@Override
	public void loadImages(CaatjaPreloader preloader, CaatjaImageLoaderCallback loadingCallback) {
		
		CaatjaFxImageService service = new ImageServiceImpl();
		
		HashMap<String, String> images = service.getImages((HashMap<String, String>) preloader.getImages());

		for (Map.Entry<String, String> entry : images.entrySet()) {
            CaatjaFxImage image = new CaatjaFxImage();
            image.loadData(entry.getValue());
            preloader.getCaatjaImages().put(entry.getKey(), image);
        }
        
        try {
            loadingCallback.onFinishedLoading();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
