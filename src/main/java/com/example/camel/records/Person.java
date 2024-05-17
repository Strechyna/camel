package com.example.camel.records;

import java.io.Serializable;

public record Person(Integer id, String name, String city) implements Serializable {
}
