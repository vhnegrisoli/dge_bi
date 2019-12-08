package br.com.dge_bi.br.com.dge_bi.config;

import com.querydsl.core.BooleanBuilder;

public class PredicateBase {

    protected BooleanBuilder builder;

    public PredicateBase() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }
}

