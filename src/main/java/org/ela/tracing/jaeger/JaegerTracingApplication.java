package org.ela.tracing.jaeger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

@SpringBootApplication
public class JaegerTracingApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(JaegerTracingApplication.class, args);
	}
	
	@Bean
	public Tracer tracer() {
	    SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv()
	            .withType(ConstSampler.TYPE)
	            .withParam(1);
	 
	    ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv()
	            .withLogSpans(true);
	 
	    Configuration config = new Configuration("kobajagi-service")
	            .withSampler(samplerConfig)
	            .withReporter(reporterConfig);
	 
	    return config.getTracer();
	}

}
