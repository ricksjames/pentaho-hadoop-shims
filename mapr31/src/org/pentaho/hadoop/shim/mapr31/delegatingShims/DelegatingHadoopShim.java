package org.pentaho.hadoop.shim.mapr31.delegatingShims;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;

import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.hadoop.shim.ConfigurationException;
import org.pentaho.hadoop.shim.HadoopConfiguration;
import org.pentaho.hadoop.shim.HadoopConfigurationFileSystemManager;
import org.pentaho.hadoop.shim.ShimVersion;
import org.pentaho.hadoop.shim.api.Configuration;
import org.pentaho.hadoop.shim.api.DistributedCacheUtil;
import org.pentaho.hadoop.shim.api.fs.FileSystem;
import org.pentaho.hadoop.shim.api.mapred.RunningJob;
import org.pentaho.hadoop.shim.mapr31.authorization.HadoopAuthorizationService;
import org.pentaho.hadoop.shim.mapr31.authorization.HasHadoopAuthorizationService;
import org.pentaho.hadoop.shim.spi.HadoopShim;

public class DelegatingHadoopShim implements HadoopShim, HasHadoopAuthorizationService {
  protected static final String SUPER_USER = "authentication.superuser.provider";
  public static final String PROVIDER_LIST = "authentication.provider.list";
  private HadoopShim delegate = null;

  @Override
  public void onLoad( HadoopConfiguration config, HadoopConfigurationFileSystemManager fsm ) throws Exception {
    delegate.onLoad( config, fsm );
  }

  @Override
  public void setHadoopAuthorizationService( HadoopAuthorizationService hadoopAuthorizationService ) throws Exception {
    delegate = hadoopAuthorizationService.getShim( HadoopShim.class );
  }

  @Override
  public ShimVersion getVersion() {
    return delegate.getVersion();
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Driver getHiveJdbcDriver() {
    return delegate.getHiveJdbcDriver();
  }

  @Override
  public Driver getJdbcDriver( String driverType ) {
    return delegate.getJdbcDriver( driverType );
  }

  @Override
  public String[] getNamenodeConnectionInfo( Configuration c ) {
    return delegate.getNamenodeConnectionInfo( c );
  }

  @Override
  public String[] getJobtrackerConnectionInfo( Configuration c ) {
    return delegate.getJobtrackerConnectionInfo( c );
  }

  @Override
  public String getHadoopVersion() {
    return delegate.getHadoopVersion();
  }

  @Override
  public Configuration createConfiguration() {
    return delegate.createConfiguration();
  }

  @Override
  public FileSystem getFileSystem( Configuration conf ) throws IOException {
    return delegate.getFileSystem( conf );
  }

  @Override
  public void configureConnectionInformation( String namenodeHost, String namenodePort, String jobtrackerHost,
      String jobtrackerPort, Configuration conf, List<String> logMessages ) throws Exception {
    delegate.configureConnectionInformation( namenodeHost, namenodePort, jobtrackerHost, jobtrackerPort, conf,
        logMessages );
  }

  @Override
  public DistributedCacheUtil getDistributedCacheUtil() throws ConfigurationException {
    return delegate.getDistributedCacheUtil();
  }

  @Override
  public RunningJob submitJob( Configuration c ) throws IOException {
    return delegate.submitJob( c );
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Class<?> getHadoopWritableCompatibleClass( ValueMetaInterface kettleType ) {
    return delegate.getHadoopWritableCompatibleClass( kettleType );
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Class<?> getPentahoMapReduceCombinerClass() {
    return delegate.getPentahoMapReduceCombinerClass();
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Class<?> getPentahoMapReduceReducerClass() {
    return delegate.getPentahoMapReduceReducerClass();
  }

  @SuppressWarnings( "deprecation" )
  @Override
  public Class<?> getPentahoMapReduceMapRunnerClass() {
    return delegate.getPentahoMapReduceMapRunnerClass();
  }
}