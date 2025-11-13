package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleCarro {
    private List<ItemCarro> items;

    public DetalleCarro() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro) {
        if (itemCarro == null || itemCarro.getProducto() == null) {
            return;
        }

        Optional<ItemCarro> itemExistente = items.stream()
                .filter(i -> i.getProducto().getId().equals(itemCarro.getProducto().getId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemCarro item = itemExistente.get();
            item.setCantidad(item.getCantidad() + itemCarro.getCantidad());
        } else {
            this.items.add(itemCarro);
        }
    }

    // Método para remover item del carrito
    public void removeItemCarro(Long productoId) {
        items.removeIf(item -> item.getProducto().getId().equals(productoId));
    }

    // Método para actualizar cantidad
    public void actualizarCantidad(Long productoId, int cantidad) {
        items.stream()
                .filter(item -> item.getProducto().getId().equals(productoId))
                .findFirst()
                .ifPresent(item -> item.setCantidad(cantidad));
    }

    // Método para limpiar el carrito
    public void clear() {
        items.clear();
    }

    public List<ItemCarro> getItem() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(ItemCarro::getSubtotal).sum();
    }

    public double getIva() {
        return getTotal() * 0.15;
    }

    public double getTotalConIva() {
        return getTotal() + getIva();
    }
}