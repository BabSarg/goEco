package eco_service.Eco.dtos;

public class ResponseDeleteEcoServiceDto {
    private boolean deleted;

    public ResponseDeleteEcoServiceDto(boolean deleted) {

        this.deleted = deleted;
    }

    public boolean isRemoved() {
        return deleted;
    }

    public void setRemoved(boolean deleted) {
        this.deleted = deleted;
    }


}
