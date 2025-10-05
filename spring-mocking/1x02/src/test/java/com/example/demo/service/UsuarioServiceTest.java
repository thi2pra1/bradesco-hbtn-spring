package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        // Arrange - Preparar os dados de teste
        Long id = 1L;
        Usuario usuarioEsperado = new Usuario(id, "João Silva", "joao.silva@email.com");

        // Configurar o mock para retornar um Optional com o usuário quando findById for chamado
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEsperado));

        // Act - Executar o método que está sendo testado
        Usuario usuarioRetornado = usuarioService.buscarUsuarioPorId(id);

        // Assert - Verificar se o resultado é o esperado
        assertNotNull(usuarioRetornado);
        assertEquals(usuarioEsperado.getId(), usuarioRetornado.getId());
        assertEquals(usuarioEsperado.getNome(), usuarioRetornado.getNome());
        assertEquals(usuarioEsperado.getEmail(), usuarioRetornado.getEmail());

        // Verificar se o método findById foi chamado uma vez com o ID correto
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        // Arrange - Preparar os dados de teste
        Long idInexistente = 999L;

        // Configurar o mock para retornar um Optional vazio quando findById for chamado
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert - Executar o método e verificar se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarUsuarioPorId(idInexistente);
        });

        // Verificar se a mensagem da exceção é a esperada
        assertEquals("Usuário não encontrado", exception.getMessage());

        // Verificar se o método findById foi chamado uma vez com o ID correto
        verify(usuarioRepository, times(1)).findById(idInexistente);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        // Arrange - Preparar os dados de teste
        Usuario usuarioParaSalvar = new Usuario("Maria Santos", "maria.santos@email.com");
        Usuario usuarioSalvo = new Usuario(2L, "Maria Santos", "maria.santos@email.com");

        // Configurar o mock para retornar o usuário salvo quando save for chamado
        when(usuarioRepository.save(usuarioParaSalvar)).thenReturn(usuarioSalvo);

        // Act - Executar o método que está sendo testado
        Usuario usuarioRetornado = usuarioService.salvarUsuario(usuarioParaSalvar);

        // Assert - Verificar se o resultado é o esperado
        assertNotNull(usuarioRetornado);
        assertNotNull(usuarioRetornado.getId());
        assertEquals(usuarioSalvo.getId(), usuarioRetornado.getId());
        assertEquals(usuarioSalvo.getNome(), usuarioRetornado.getNome());
        assertEquals(usuarioSalvo.getEmail(), usuarioRetornado.getEmail());

        // Verificar se o método save foi chamado uma vez com o usuário correto
        verify(usuarioRepository, times(1)).save(usuarioParaSalvar);
    }
}
