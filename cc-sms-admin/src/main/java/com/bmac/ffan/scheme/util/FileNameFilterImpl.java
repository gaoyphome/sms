package com.bmac.ffan.scheme.util;

import java.io.File;
import java.io.FilenameFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileNameFilterImpl
  implements FilenameFilter
{
  private static final Logger logger = LoggerFactory.getLogger(FileNameFilterImpl.class);
  private File file;
  private String matchFileStr;

  public FileNameFilterImpl(File file, String matchFileStr)
  {
    this.file = file;
    this.matchFileStr = matchFileStr;
  }

  public boolean accept(File dir, String matchFileStr) {
    logger.debug(this.file.getName() + "|" + this.matchFileStr);
    String filename = dir.getName().toLowerCase();

    return (!(filename.startsWith(matchFileStr)));
  }
}