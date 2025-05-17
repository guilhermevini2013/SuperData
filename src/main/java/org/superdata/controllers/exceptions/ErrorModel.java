package org.superdata.controllers.exceptions;

public record ErrorModel(String path, String message, int statusCode) {
}
