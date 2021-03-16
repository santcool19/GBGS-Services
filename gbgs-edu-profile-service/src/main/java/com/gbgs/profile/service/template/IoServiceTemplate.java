package com.gbgs.profile.service.template;

import java.io.File;
import java.util.List;

public interface IoServiceTemplate {

    List<String> searchFile(File directory, String searchFile);

    List<String> searchWord(File directory, String searchWord);
}
