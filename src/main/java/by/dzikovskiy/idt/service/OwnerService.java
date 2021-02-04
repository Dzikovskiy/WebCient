package by.dzikovskiy.idt.service;

import by.dzikovskiy.idt.entity.Item;
import by.dzikovskiy.idt.entity.Owner;
import by.dzikovskiy.idt.entity.StackResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    public List<Owner> getOwnersFromStackResponse(StackResponse stackResponse) {
        return stackResponse.getItems().stream().map(Item::getOwner).collect(Collectors.toList());
    }
}
