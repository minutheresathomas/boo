package com.wm.bfd.oo;

import com.wm.bfd.oo.yaml.Yaml;

import com.google.inject.Singleton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class ClientConfig {

  public static final File ONEOPS_CONFIG = new File(new File(System.getProperty("user.home"), ".boo"), "config");
  public static final String ONEOPS_DEFAULT_PROFILE = "default";
  private Yaml yaml;

  // For add user component in design
  public static final String SSH_KEY = "authorized_keys";
  public static final String USER_NAME = "username";
  public static final String USER = "user";

  // Compute size
  public static final String SIZE = "size";

  /** The Constant COMPUTE. */
  public static final String COMPUTE = "compute";

  /**
   * Instantiates a new client config.
   *
   * @param booYamlFile the file
   * @param profile the profile
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ClientConfig(File booYamlFile, String profile) throws IOException {
    ClientConfigReader reader = new ClientConfigReader();
    ClientConfigInterpolator interpolator = new ClientConfigInterpolator();
    this.yaml = reader.read(interpolator.interpolate(booYamlFile, ONEOPS_CONFIG, profile));
  }
  
  /**
   * Create a ClientConfig by parsing an InputStream.
   * @param input the inputstream
   * @param profile the profile
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ClientConfig(InputStream input, String profile) throws IOException {
    ClientConfigReader reader = new ClientConfigReader();
    ClientConfigInterpolator interpolator = new ClientConfigInterpolator();
    this.yaml = reader.read(interpolator.interpolate(input, ONEOPS_CONFIG, profile));
  }

  public Yaml getYaml() {
    return yaml;
  }
}
