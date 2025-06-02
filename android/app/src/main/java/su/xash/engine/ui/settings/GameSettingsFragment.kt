package su.xash.engine.ui.settings

// https://github.com/Velaron/xash3d-fwgs/blob/def07f4e5b3110c737d1466026c922373b37d487/android/app/src/main/java/su/xash/engine/ui/settings/GameSettingsFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import su.xash.engine.databinding.FragmentGameSettingsBinding
import su.xash.engine.ui.library.LibraryViewModel

class GameSettingsFragment : Fragment() {
    private var _binding: FragmentGameSettingsBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val game = libraryViewModel.selectedItem.value!!

        binding.gameCard.apply {
            gameTitle.text = game.title

            if (game.icon != null) {
                gameIcon.setImageBitmap(game.icon)
            } else {
                gameIcon.visibility = View.GONE
            }

            if (game.cover != null) {
                gameCover.setImageBitmap(game.cover)
            } else {
                gameCover.visibility = View.GONE
            }

            buttonsContainer.visibility = View.GONE
        }

        childFragmentManager.beginTransaction()
            .add(binding.settingsFragment.id, GameSettingsPreferenceFragment(game))
            .commit();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
