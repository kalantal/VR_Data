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

    companion object {
        val CLUSTER_NAME_PROPERTY = "cluster.name"
        val HOME_PATH_PROPERTY = "path.home"

        val ES_HOST_ENV_VAR_NAME = "SDVR_ES_HOST"
        val ES_PORT_ENV_VAR_NAME = "SDVR_ES_PORT"

        val DEFAULT_TRANSPORT_CLIENT_PORT = "9300"
        val CLUSTER_NAME = "my-application"
        val HOME_PATH = "/usr/share/home"
    }

    @Bean
    fun provideElasticSearchSettings(): Settings {
        return Settings.builder()
                .put(CLUSTER_NAME_PROPERTY, CLUSTER_NAME)
                .put(HOME_PATH_PROPERTY, HOME_PATH)
                .build()
    }

    @Bean
    fun provideClient(settings: Settings): Client {
        //Get host and port from environment variables
        val envMap = System.getenv()
        val host = envMap[(ES_HOST_ENV_VAR_NAME)]
        var port: Int?
        try {
            port = envMap.getOrDefault(ES_PORT_ENV_VAR_NAME, DEFAULT_TRANSPORT_CLIENT_PORT).toInt()
        } catch (e: Exception) {
            //TODO: Add logger functionality
            print(e.message)
            throw IllegalArgumentException("Unable to convert $ES_PORT_ENV_VAR_NAME environment variable to an Int")
        }
        return PreBuiltTransportClient(settings)
                .addTransportAddress(InetSocketTransportAddress(InetAddress.getByName(host), port!!))
    }

}
