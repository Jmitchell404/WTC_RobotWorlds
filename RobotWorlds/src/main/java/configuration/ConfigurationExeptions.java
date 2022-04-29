package configuration;

class ConfigurationException extends RuntimeException{

    public ConfigurationException(String errorMessage) {
        super (errorMessage);
    }
}