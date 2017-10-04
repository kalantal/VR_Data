package com.stmk.sddatavr.search

import org.elasticsearch.client.Client
import org.elasticsearch.node.Node
import org.elasticsearch.node.NodeBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Krishna Chaitanya Kandula on 10/3/17.
 */
@Configuration
class Config {

    @Bean(name = arrayOf("ClusterName"))
    fun provideClusterName(): String = "elasticsearch"

//    @Bean
//    fun provideNode(clusterName: String): Node = NodeBuilder.nodeBuilder().clusterName(clusterName).client(true).node()
//
//    @Bean
//    fun provideClient(node: Node): Client = node.client()
}