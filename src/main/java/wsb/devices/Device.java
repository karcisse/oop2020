package wsb.devices;

import wsb.database.MappedToSql;

public abstract class Device {
        @MappedToSql
        final public String producer;
        @MappedToSql
        final public String model;
        public Double value;

    public Device(String producer, String model) {
        this.producer = producer;
        this.model = model;
    }

   public abstract void turnOn();

    public String getModel(){
        return this.model;
    }

    public String getProducer() {
        return producer;
    }
}
