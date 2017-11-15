package com.stmk.sddatavr.search

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress

/**
 * Created by Krishna Chaitanya Kandula on 10/3/17.
 */
@Configuration
class Config {

    @Bean(name = arrayOf("ClusterName"))
    fun provideClusterName(): String = "my-application"

    @Bean(name = arrayOf("HomePath"))
    fun provideHomePath(): String = "/usr/share/home"

    @Bean
    fun provideElasticSearchSettings(@Qualifier("ClusterName") clusterName: String,
                                     @Qualifier("HomePath") path: String): Settings {
        return Settings.builder()
                .put("cluster.name", clusterName)
                .put("path.home", path)
                .build()
    }

    @Bean
    fun provideClient(settings: Settings): Client {
        //Get host and port from environment variables
        val envMap = System.getenv()
        val host = envMap[("SDVR_ES_HOST")]
        var port: Int?
        try {
            port = envMap.getOrDefault("SDVR_ES_PORT", "9200").toInt()
        } catch (e: Exception) {
            //TODO: Add logger functionality
            print(e.message)
            throw IllegalArgumentException("Unable to convert 'SDVR_ES_PORT' environment variable to an Int")
        }
        return PreBuiltTransportClient(settings)
                .addTransportAddress(InetSocketTransportAddress(InetAddress.getByName(host), port!!))
    }

}
