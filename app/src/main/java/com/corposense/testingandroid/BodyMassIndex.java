package com.corposense.testingandroid;

class BodyMassIndex {

    private double weight;
    private double height;

    public BodyMassIndex(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public Double calculate(){
            return this.weight / (this.height * this.height);
        }

}
