// @formatter:off
/**
 * Copyright 2021 Bernard Ladenthin bernard.ladenthin@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
// @formatter:on
package net.ladenthin.bitcoinaddressfinder;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import net.ladenthin.bitcoinaddressfinder.configuration.CProducerJavaSecretsFiles;
import org.bitcoinj.core.NetworkParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerJavaSecretsFiles extends ProducerJava {

    private final Logger logger = LoggerFactory.getLogger(ProducerJavaSecretsFiles.class);
    
    private final CProducerJavaSecretsFiles producerJavaSecretsFiles;

    private final ReadStatistic readStatistic = new ReadStatistic();

    public ProducerJavaSecretsFiles(CProducerJavaSecretsFiles producerJavaSecretsFiles, AtomicBoolean shouldRun, Consumer consumer, KeyUtility keyUtility, Random random) {
        super(producerJavaSecretsFiles, shouldRun, consumer, keyUtility, random);
        this.producerJavaSecretsFiles = producerJavaSecretsFiles;
    }

    @Override
    public void initProducer() {
    }

    @Override
    public void produceKeys() {
        final NetworkParameters networkParameters = new NetworkParameterFactory().getOrCreate();
        
        FileHelper fileHelper = new FileHelper();
        List<File> files = fileHelper.stringsToFiles(producerJavaSecretsFiles.files);
        fileHelper.assertFilesExists(files);
        
        logger.info("writeAllAmounts ...");
        logger.info("Iterate secrets files ...");
        try {
            for (File file : files) {
                SecretsFile secretsFile = new SecretsFile(
                    networkParameters,
                    file,
                    producerJavaSecretsFiles.secretFormat,
                    readStatistic,
                    this::processSecret,
                    this.shouldRun
                );

                logger.info("process: " + file.getAbsolutePath());
                secretsFile.readFile();
                logger.info("finished: " + file.getAbsolutePath());

                logProgress();
                logger.info("... iterate secrets files done.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void logProgress() {
        logger.info("Progress: Unsupported: " + readStatistic.unsupported + ". Errors: " + readStatistic.errors.size() + ". Current File progress: " + String.format("%.2f", readStatistic.currentFileProgress) + "%.");
    }

    @Override
    public void releaseProducers() {
    }
}
