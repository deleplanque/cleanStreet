package com.cleanStreet.webApp.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class ClarifaiApi {
	// Provide your Client ID
	private final static String CLIENT_ID = "Your Client ID";

	// Provider Your Client Secret Key
	private final static String CLIENT_SECRET_KEY = "dec09c51cf9a4599a856fe5c051986fd";

	public static List<String> recognize(String imageUrl) {

		// Defining List Object
		List<String> resultList = new ArrayList<String>();

		if (imageUrl != null && !imageUrl.isEmpty()) {

			final ClarifaiClient client = new ClarifaiBuilder(CLIENT_SECRET_KEY).buildSync();

			final List<ClarifaiOutput<Concept>> predictionResults = client.getDefaultModels().generalModel().predict()
					.withInputs(ClarifaiInput.forImage(new File(imageUrl))).executeSync().get();

			if (predictionResults != null && predictionResults.size() > 0) {

				// Prediction List Iteration
				for (int i = 0; i < predictionResults.size(); i++) {

					ClarifaiOutput<Concept> clarifaiOutput = predictionResults.get(i);

					List<Concept> concepts = clarifaiOutput.data();

					if (concepts != null && concepts.size() > 0) {
						for (int j = 0; j < concepts.size(); j++) {

							resultList.add(concepts.get(j).name());
						}
					}
				}
			}

		}
		return resultList;
	}
}
